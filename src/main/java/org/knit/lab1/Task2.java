package org.knit.lab1;

/***
 * Задача 2
 * Измененный класс SimpleUrl для парсинга URL.
 */
public class Task2 {
    public static void main(String[] args) {

        String urlValue = "https://test.ru/test/1072/page.jsp?intParam=12345&doubleParam=3.14&textParameter=someText";

        SimpleUrl url = new SimpleUrl();
        url.parseUrl(urlValue);

        // Выводим результат
        System.out.println(url);
    }
}

class SimpleUrl {
    private String protocol;
    private String host;
    private String domainZone;
    private String subdomain;
    private String path;
    private String fileName;
    private String fileExtension;
    private String integerParameter;
    private String decimalParameter;
    private String stringParameter;

    // Метод для парсинга URL
    public void parseUrl(String url) {
        String[] protocolSplit = url.split("://");
        this.protocol = protocolSplit[0];

        String[] hostAndPath = protocolSplit[1].split("/", 2);
        this.host = hostAndPath[0];

        String[] hostParts = host.split("\\.");
        if (hostParts.length > 1) {
            this.subdomain = hostParts[0];
            this.domainZone = hostParts[1];
        }

        String[] pathAndParams = hostAndPath[1].split("\\?");
        this.path = "/" + pathAndParams[0];

        String[] pathParts = path.split("/");
        String lastPart = pathParts[pathParts.length - 1];
        String[] fileParts = lastPart.split("\\.");
        if (fileParts.length > 1) {
            this.fileName = fileParts[0];
            this.fileExtension = fileParts[1];
        }

        if (pathAndParams.length > 1) {
            parseParameters(pathAndParams[1]);
        }
    }

    // Метод для парсинга параметров
    private void parseParameters(String params) {
        String[] paramArray = params.split("&");
        for (String param : paramArray) {
            String[] keyValue = param.split("=");
            switch (keyValue[0]) {
                case "intParam":
                    this.integerParameter = keyValue[1];
                    break;
                case "doubleParam":
                    this.decimalParameter = keyValue[1];
                    break;
                case "textParameter":
                    this.stringParameter = keyValue[1];
                    break;
            }
        }
    }

    // Геттеры и сеттеры
    public String getProtocol() { return protocol; }
    public void setProtocol(String protocol) { this.protocol = protocol; }
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    public String getDomainZone() { return domainZone; }
    public void setDomainZone(String domainZone) { this.domainZone = domainZone; }
    public String getSubdomain() { return subdomain; }
    public void setSubdomain(String subdomain) { this.subdomain = subdomain; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileExtension() { return fileExtension; }
    public void setFileExtension(String fileExtension) { this.fileExtension = fileExtension; }
    public String getIntegerParameter() { return integerParameter; }
    public void setIntegerParameter(String integerParameter) { this.integerParameter = integerParameter; }
    public String getDecimalParameter() { return decimalParameter; }
    public void setDecimalParameter(String decimalParameter) { this.decimalParameter = decimalParameter; }
    public String getStringParameter() { return stringParameter; }
    public void setStringParameter(String stringParameter) { this.stringParameter = stringParameter; }

    // Переопределение метода toString
    @Override
    public String toString() {
        return "SimpleUrl {\n" +
                "  protocol = " + protocol + "\n" +
                "  host = " + host + "\n" +
                "  domainZone = " + domainZone + "\n" +
                "  subdomain = " + subdomain + "\n" +
                "  path = " + path + "\n" +
                "  fileName = " + fileName + "\n" +
                "  fileExtension = " + fileExtension + "\n" +
                "  integerParameter = " + integerParameter + "\n" +
                "  decimalParameter = " + decimalParameter + "\n" +
                "  stringParameter = " + stringParameter + "\n" +
                '}';
    }
}
