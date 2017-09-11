import org.apache.commons.lang3.StringUtils

/**
 * 19.05.2017.
 * TODO - DOCUMENTATION
 * @author merbel
 */
class XmlReader {

    int size;
    Map<String, Row> rows = new TreeMap<String, Row>()

    private static final String INPUT_PATH = "/Users/kwalczak/Desktop/SROWASP/results";

    XmlReader(int size) {
        this.size = size;
    }

    void readDependencyChecks() {
        new File(INPUT_PATH).eachDir {
            line ->
                println(line)
                line.eachFile {
                    file ->
                        println(file)
                        parseFile(file.getAbsolutePath(), line.getName())
                }
        }



        new File("output/project", "report.csv").withWriter('utf-8') { writer ->
            StringBuilder builder = new StringBuilder();
            for (Row row : rows.values()) {
                builder.append(row)
                builder.append("\n")
            }
            writer.write(builder.toString())
        }
    }

    void parseFile(String path, String module) {
        def xmlFile = new XmlSlurper().parse(path)
        xmlFile.dependencies.dependency.each { lib ->
            println(lib.fileName)
            lib.vulnerabilities.vulnerability.each { vul ->
                println("vul.severity" + vul.severity)
                if (StringUtils.equals(vul.severity.toString(), "High")) {
                    println(lib.fileName + "is affected!")
                    String substringedFilePath = StringUtils.substringAfter(lib.filePath.toString(), ".m2-owasp")
                    println("Rows before: " + rows.size())
                    addRow(substringedFilePath, vul.name.toString(), vul.severity.toString(), vul.description.toString(), module)
                    println("Rows after: " + rows.size())
                } else {
                    println("vul.severity equals: " + vul.severity)
                }
            }

        }
        println(rows.size())
    }

    private void addRow(String artifact, String vulName, String severity, String explanation, String module) {
        if (rows.get(vulName) == null) {
            rows.put(vulName, new Row(artifact, vulName, severity, explanation, module))
        } else {
            rows.get(vulName).addArtifact(artifact)
            rows.get(vulName).addModule(module)
        }
    }


}
