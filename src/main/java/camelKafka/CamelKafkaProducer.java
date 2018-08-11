package camelKafka;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.HashMap;
import java.util.Map;

public class CamelKafkaProducer {

    public static void main(String[] args) throws Exception {

        final CamelContext context = new DefaultCamelContext();

        try {
            context.addRoutes(new RouteBuilder() {
                public void configure() {

                    KafkaComponent kafka = new KafkaComponent();
                    kafka.setBrokers("178.128.153.12:9092");
                    context.addComponent("kafka", kafka);
//                    rout pick up the mesage and sends to kafka topic
                    from("direct:pushtoTopic").routeId("DirectToKafka")
                            .to("kafka:test3").log("${headers}");

                }

            });


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ProducerTemplate producerTemplate = context.createProducerTemplate();
        context.start();

        Map<String, Object> headers = new HashMap<String, Object>();

        headers.put(KafkaConstants.PARTITION_KEY, 0);
        headers.put(KafkaConstants.KEY, "1");
        for(int i=0;i<=500;i++){
            producerTemplate.sendBodyAndHeaders("direct:pushtoTopic"," Hi Hello " + i, headers);
        }


        Thread.sleep(5 * 60 * 1000);

        context.stop();

    }
}
