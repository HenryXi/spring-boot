# Download file in Spring MVC example
Each download method is transfer data from server to client. Use Spring MVC is the same way to do that. The first thing
is find the downloaded file. The second thing is read it and transfers it. In this blog I will show you how to use Spring
MVC download file. For quickly start I use Spring Boot. The code also works fine in Spring.

**Project structure**
```
├─main       
│  ├─java   
│  │  └─com
│  │      └─henryxi 
│  │          └─downlaod     
│  │                  DownloadController.java    
│  │
│  └─resources       
│          application.properties        
│          target_file.txt      
│    
└─test       
    └─java  
```

**DownloadController.java**
```java
@Controller
@EnableAutoConfiguration
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response) {
        try {
            DefaultResourceLoader loader = new DefaultResourceLoader();
            InputStream is = loader.getResource("classpath:target_file.txt").getInputStream();
            IOUtils.copy(is, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename=target_file.txt");
            response.flushBuffer();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DownloadController.class, args);
    }
}
```

The most important line `response.setHeader("Content-Disposition", "attachment; filename=target_file.txt");`. It will
make browser download this file instead of display it. Access the download link in IE or Firefox both of them will open
"save as" dialog. If you use Chrome to access the download link it will download it directly.