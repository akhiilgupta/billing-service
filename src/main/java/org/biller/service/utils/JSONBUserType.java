package org.biller.service.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONBUserType implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[] {Types.JAVA_OBJECT};
  }

  @Override
  public Class<?> returnedClass() {
    return JSONObject.class;
  }

  @Override
  public Object deepCopy(final Object value) {
    try {
      // use serialization to create a deep copy
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(value);
      oos.flush();
      oos.close();
      bos.close();

      ByteArrayInputStream bais = new ByteArrayInputStream(bos.toByteArray());
      return new ObjectInputStream(bais).readObject();
    } catch (ClassNotFoundException | IOException ex) {
      throw new HibernateException(ex);
    }
  }

  @Override
  public boolean isMutable() {
    return true;
  }

  @Override
  public Serializable disassemble(final Object value) {
    return (Serializable) this.deepCopy(value);
  }

  @Override
  public Object assemble(final Serializable cached, final Object owner) {
    return this.deepCopy(cached);
  }

  @Override
  public Object replace(final Object original, final Object target, final Object owner) {
    return this.deepCopy(original);
  }

  @Override
  public boolean equals(final Object obj1, final Object obj2) {
    if (obj1 == null) {
      return obj2 == null;
    }
    return obj1.equals(obj2);
  }

  @Override
  public int hashCode(final Object obj) {
    return obj.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session,
      Object owner) throws SQLException {

    final String cellContent = rs.getString(names[0]);
    if (cellContent == null) {
      return null;
    }
    try {
      JSONParser parser = new JSONParser();
      return parser.parse(cellContent);
    } catch (final Exception ex) {
      throw new RuntimeException(
          "Failed to convert PostgreSQL jsonb to JSONObject : " + ex.getMessage(), ex);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement ps, Object value, int idx,
      SharedSessionContractImplementor session) throws SQLException {

    if (value == null) {
      ps.setNull(idx, Types.OTHER);
      return;
    }
    try {
      final ObjectMapper mapper = new ObjectMapper();
      final StringWriter w = new StringWriter();
      mapper.writeValue(w, value);
      w.flush();
      ps.setObject(idx, w.toString(), Types.OTHER);
    } catch (final Exception ex) {
      throw new RuntimeException("Failed to convert JSON to String: " + ex.getMessage(), ex);
    }

  }
}
