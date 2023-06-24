package net.consolejs.satisfactory.compile.error;

import org.glassfish.grizzly.http.server.ErrorPageGenerator;
import org.glassfish.grizzly.http.server.Request;

public class DefaultErrorPageGenerator implements ErrorPageGenerator {
    private static final String STYLING = """
            <style>
                            *{
                                transition: all 0.6s;
                            }
                            
                            html {
                                height: 100%;
                            }
                            
                            body{
                                font-family: 'Lato', sans-serif;
                                color: #888;
                                margin: 0;
                            }
                            
                            #main{
                                display: table;
                                width: 100%;
                                height: 100vh;
                                text-align: center;
                            }
                            
                            .fof{
                                  display: table-cell;
                                  vertical-align: middle;
                            }
                            
                            .fof h1{
                                  font-size: 50px;
                                  display: inline-block;
                                  padding-right: 12px;
                                  animation: type .5s alternate infinite;
                            }
                            
                            @keyframes type{
                                  from{box-shadow: inset -3px 0px 0px #888;}
                                  to{box-shadow: inset -3px 0px 0px transparent;}
                            }
                        </style>
            """;

    @Override
    public String generate(Request request, int responseCode, String reasonPhrase, String description,
                           Throwable throwable) {
        return "<html>"
                .concat("<head>")
                .concat(String.format("<title>Satisfactory - %d</title>", responseCode))
                .concat(STYLING)
                .concat("</head>")
                .concat("<body>")
                .concat("<div id=\"main\">")
                .concat("<div class=\"fof\">")
                .concat(String.format("<h1>Error %d</h1>", responseCode))
                .concat("</div>")
                .concat("</div>")
                .concat("</body>")
                .concat("</html>");
    }
}
