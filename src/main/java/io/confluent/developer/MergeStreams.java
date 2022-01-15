package io.confluent.developer;

import io.confluent.developer.avro.SongEvent;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MergeStreams {
    public Topology buildTopology(Properties props) {
        StreamsBuilder builder = new StreamsBuilder();
        final String rockTopic = props.getProperty("input.rock.topic.name");
        final String classicalTopic = props.getProperty("input.classical.topic.name");
        final String allGenresTopic = props.getProperty("output.topic.name");
        KStream<String, SongEvent> rockSongs = builder.stream(rockTopic);
        KStream<String, SongEvent> classicalSongs = builder.stream(classicalTopic);
        return builder.build();
    }

    private static Properties loadProperties(String fileName) throws IOException {
        Properties props = new Properties();
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            props.load(inputStream);
        }
        return props;
    }

    public static void main(String[] args) {

    }
}
