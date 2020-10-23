package app.messages.repository;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.messages.model.Message;
import app.messages.web.MessageData;

@Component
public class MessageRepository {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  public MessageRepository(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
  
  public Message saveMessage(Message message) {
    Session session = sessionFactory.getCurrentSession();
    session.save(message);
    return message;
  }
  public Message deleteMessage(MessageData data) {
    List<Message> list = this.getMessages();
    Session session = sessionFactory.getCurrentSession();
    Message msg;
    Iterator<Message> iterator = list.iterator();
    while(iterator.hasNext()){
      msg = iterator.next();
      if(msg.getId() == data.getId()){
        session.delete(msg);
        return msg;
      }
    }
    return null;
  }
  public List<Message> getMessages() {
    Session session = sessionFactory.getCurrentSession();
    String hql = "from Message";
    Query<Message> query = session.createQuery(hql, Message.class);
    return query.list();
  }
}
