package it.discovery.persistence.strategy.naming;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.stereotype.Component;

@Component
public class UpperCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier logicalName, JdbcEnvironment context) {
        return Identifier.toIdentifier(convertToUpperCase(logicalName.getText()), logicalName.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment context) {
        return Identifier.toIdentifier(convertToUpperCase(logicalName.getText()), logicalName.isQuoted());
    }

    private String convertToUpperCase(String name) {
        StringBuilder builder = new StringBuilder(name.length());
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (i != 0 && Character.isUpperCase(ch)) {
                builder.append('_');
            }
            builder.append(Character.toUpperCase(ch));
        }

        return builder.toString();
    }
}
