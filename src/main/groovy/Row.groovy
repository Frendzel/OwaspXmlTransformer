import org.apache.commons.lang3.StringUtils

/**
 * 19.05.2017.
 * TODO - DOCUMENTATION
 * @author merbel
 */
class Row {

    String artifact
    String explanation

    Row(String artifact, String explanation) {
        this.artifact = artifact
        this.explanation = explanation
    }


    @Override
    public String toString() {
        return artifact + ";;" + explanation;
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Row row = (Row) o

        if (!StringUtils.equals(artifact, row.artifact)) return false
        if (!StringUtils.equals(explanation, row.explanation)) return false

        return true
    }

    int hashCode() {
        int result
        result = (artifact != null ? artifact.hashCode() : 0)
        result = 31 * result + (explanation != null ? explanation.hashCode() : 0)
        return result
    }
}
