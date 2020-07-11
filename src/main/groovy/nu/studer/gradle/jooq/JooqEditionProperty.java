package nu.studer.gradle.jooq;

import org.gradle.api.Project;

import static java.util.Objects.requireNonNull;

final class JooqEditionProperty {

    private static final String PROJECT_PROPERTY = "jooqEdition";
    private static final JooqEdition DEFAULT = JooqEdition.OSS;

    final JooqEdition jooqEdition;

    private JooqEditionProperty(JooqEdition jooqEdition) {
        this.jooqEdition = requireNonNull(jooqEdition);
    }

    static void applyDefaultEdition(Project project) {
        project.getExtensions().getExtraProperties().set(PROJECT_PROPERTY, DEFAULT);
    }

    static JooqEditionProperty fromProject(Project project) {
        Object value = project.getExtensions().getExtraProperties().get(PROJECT_PROPERTY);
        return value instanceof String ? from(JooqEdition.valueOf((String) value)) : from((JooqEdition) value);
    }

    private static JooqEditionProperty from(JooqEdition jooqEdition) {
        return new JooqEditionProperty(jooqEdition);
    }

    String asGroupId() {
        return jooqEdition.getGroupId();
    }

}