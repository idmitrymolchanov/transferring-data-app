package psychotest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile(value = "prod")
public class DatabaseCloudProperties {

    @Value("${psychotest.jndi.datasource.one}")
    private String jndiStringOne;

    @Value("${psychotest.jndi.datasource.two}")
    private String jndiStringTwo;

    public String getJndiStringOne() {
        return jndiStringOne;
    }

    public String getJndiStringTwo() {
        return jndiStringTwo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JndiProdProperties{");
        sb.append("jndiStringOne='").append(jndiStringOne).append('\'');
        sb.append(", jndiStringTwo='").append(jndiStringTwo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
