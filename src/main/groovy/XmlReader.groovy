import org.apache.commons.lang3.StringUtils

/**
 * 19.05.2017.
 * TODO - DOCUMENTATION
 * @author merbel
 */
class XmlReader {

    int size;

    XmlReader(int size) {
        this.size = size;
    }

    public void readDependencyChecks() {
        Set<Row> rows = new HashSet<Row>()
        for (int i = 0; i < size; i++) {
            def xmlFile = new XmlSlurper().parse("input/project/dependency-check-report" + (i + 1) + ".xml")
            xmlFile.dependencies.dependency.each { lib ->
                println(lib.fileName)
                lib.vulnerabilities.vulnerability.each { vul ->
                    println("vul.severity" + vul.severity)
                    if (StringUtils.equals(vul.severity.toString(), "High")) {
                        println(lib.fileName + "is affected!")
                        String substringedFilePath = StringUtils.substringAfter(lib.filePath.toString(), ".m2-owasp")
                        println("Rows before: " + rows.size())
                        rows.add(new Row(String.join(";;", substringedFilePath, vul.name.toString(), vul.severity.toString()), vul.description.toString()))
                        println("Rows after: " + rows.size())
                    } else {
                        println("vul.severity equals: "+vul.severity)
                    }
                }

            }
            println(rows.size())
        }
        new File("output/project", "report.csv").withWriter('utf-8') { writer ->
            StringBuilder builder = new StringBuilder();
            for (Row row: rows){
                builder.append(row)
                builder.append("\n")
            }
            writer.write(builder.toString())
        }
    }


}
