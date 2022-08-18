# Spring EventListener example
The EventListener annotation in the spring framework can be used to decouple logic. The trigger of the event does not 
need to pay attention to the processing logic of the event.

**SimpleController**
```java
@Controller
@SpringBootApplication
public class SimpleController {

    @Autowired
    private ApplicationEventPublisher applicationContext;

    @RequestMapping(value = "/publishEvent", method = RequestMethod.GET)
    @ResponseBody
    public String publishEvent() {
        applicationContext.publishEvent(new MyEvent("luck data"));
        return "Hello Spring Boot";
    }

    public static void main(String[] args) {
        SpringApplication.run(SimpleController.class, args);
    }
}
```
**MyEventListener**
```java
@Component
public class MyEventListener {

    @EventListener
    public void myEvent(PayloadApplicationEvent<MyEvent> myEvent) {
        System.out.println("my event:" + myEvent.toString());
    }
}
```
**MyEvent**
```java
public class MyEvent {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MyEvent(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "data='" + data + '\'' +
                '}';
    }
}
```

Enjoy!