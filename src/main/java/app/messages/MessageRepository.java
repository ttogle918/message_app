package app.messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {

  private static final Log logger = LogFactory.getLog(MessageRepository.class);

  private DataSource dataSource;

  public MessageRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public Message saveMessage(Message message) {
    logger.info("repository start");
    Connection c = DataSourceUtils.getConnection(dataSource);
    try {
      logger.info("saveMsg");
      String insertSql = "INSERT INTO messages (id, text, created_date) VALUE (null, ?, ?)";
      
      PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
      // ps = c.prepareStatement(insertSql, new String[]{"ID"});
      // Prepare the parameters for the SQL
      logger.info("ps");
      
      ps.setString(1, message.getText());
      ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
      logger.info("set ps");
      
      int rowsAffected = ps.executeUpdate();
      logger.info(rowsAffected+"ps executeupdate");
      if (rowsAffected > 0) {
        // Getting the newly saved message id
        ResultSet result = ps.getGeneratedKeys();
        logger.info("ps getgeneratekeys"+result);

        if (result.next()) {
          int id = result.getInt(1);
          logger.info("result id, return msg, repository"+id);

          return new Message(id, message.getText(), message.getCreatedDate());
        } else {
          logger.error("Failed to retrieve id. No row in result set");
          return null;
        }
      } else {
        // Insert did not succeed
        return null;
      }
    } catch (SQLException ex) {
      logger.error("Failed to save message", ex);
      try {
        c.close();
      } catch (SQLException e) {
        logger.error("Failed to close connection", e);
      }
    } finally {
      DataSourceUtils.releaseConnection(c, dataSource);
    }
    return null;
  }
}
