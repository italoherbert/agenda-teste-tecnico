package italo.agenda.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@Retention(RetentionPolicy.RUNTIME)
@Sql("/data/data.sql")
@Sql(scripts = {"/data/data-drop.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public @interface DBSession {
    
}
