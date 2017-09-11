import org.apache.commons.lang3.StringUtils

/**
 * 19.05.2017.
 * TODO - DOCUMENTATION
 * @author merbel
 */
class Row {

    public static final String SEPARATOR = ";;"
    Set<String> artifacts = new HashSet<>()
    String vulName
    String severity
    String explanation
    Set<String> modules = new HashSet<>()


    Row(String artifact, String vulName, String severity, String explanation, String module) {
        this.explanation = explanation
        this.vulName = vulName
        this.severity = severity
        addArtifact(artifact)
        addModule(module)
    }


    @Override
    String toString() {
        StringBuilder sb = new StringBuilder();
        for (String artifact : artifacts) {
            sb.append(artifact)
            sb.append("\n")
        }
        sb.append(SEPARATOR)
        sb.append(vulName)
        sb.append(SEPARATOR)
        sb.append(severity)
        sb.append(SEPARATOR)
        sb.append(explanation)
        sb.append(SEPARATOR)
        for (String module : modules) {
            sb.append(module)
            sb.append(",")
        }
        sb.toString()
    }

    void addArtifact(String artifact) {
        this.artifacts.add(artifact)
    }

    void addModule(String module){
        this.modules.add(module)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Row row = (Row) o

        if (!StringUtils.equals(artifacts, row.artifacts)) return false
        if (!StringUtils.equals(vulName, row.vulName)) return false
        if (!StringUtils.equals(severity, row.severity)) return false
        if (!StringUtils.equals(explanation, row.explanation)) return false

        return true
    }

    int hashCode() {
        int result
        result = (artifacts != null ? artifacts.hashCode() : 0)
        result = 31 * result + (vulName != null ? vulName.hashCode() : 0)
        result = 31 * result + (severity != null ? severity.hashCode() : 0)
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0)
        return result
    }
}
