/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wikiedits;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
/**
 * Implements a streaming windowed version of the "WordCount" program.
 *
 * <p>This program connects to a server socket and reads strings from the socket.
 * The easiest way to try this out is to open a text server (at port 12345)
 * using the <i>netcat</i> tool via
 * <pre>
 * nc -l 12345
 * </pre>
 * and run this example with the hostname and the port as arguments.
 */
@SuppressWarnings("serial")
public class Exercise {
	public class WC {public String word; public int count;}
	public static void main(String[] args) throws Exception {

		// the host and the port to connect to
		final String hostname;
		final int port;
		DataSet<String> textt ;
		DataSet<String> textttt;
		// get the execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		final ExecutionEnvironment env1 = ExecutionEnvironment.getExecutionEnvironment();
		try {
			final ParameterTool params = ParameterTool.fromArgs(args);
			hostname = params.has("hostname") ? params.get("hostname") : "localhost";
			port = params.getInt("port");
			
				textt = env1.readTextFile(params.get("input"));
				textttt = env1.readTextFile(params.get("input1"));

		} catch (Exception e) {
			System.err.println("No port specified. Please run 'SocketWindowWordCount " +
				"--hostname <hostname> --port <port>', where hostname (localhost by default) " +
				"and port is the address of the text server");
			System.err.println("To start a simple text server, run 'netcat -l <port>' and " +
				"type the input text into the command line");
			return;
		}
		DataSet<Tuple2<String, Integer>> textt1 = textt.map(new MapFunction<String, WordWithMap>() {

			@Override
			public WordWithMap map(String arg0) throws Exception {
				// TODO Auto-generated method stub
				String a[]= arg0.split(",");
				String key = a[0];
				String value = a[1].trim();
				
				return new WordWithMap(key,value);
			}
		}).flatMap(new FlatMapFunction<WordWithMap, Tuple2<String, Integer>>() {

			@Override
			public void flatMap(WordWithMap arg0, Collector<Tuple2<String, Integer>> arg1) throws Exception {
				String value = arg0.word1;
				for(String word:value.split("\\s")) {
					arg1.collect(new Tuple2<String, Integer>(word.trim(),1));
				}
				
			}
		}).groupBy(0).sum(1);
		textt1.print();
		///
		DataSet<Tuple2<String, Double>> textttt1 = textttt.map(new MapFunction<String, Tuple2<String, Double>>() {

			@Override
			public Tuple2<String, Double> map(String arg0) throws Exception {
				// TODO Auto-generated method stub
				String a[]= arg0.split("\\s");
				String key = a[2];
				Double value = Double.parseDouble(a[1]);
				
				return new Tuple2<String, Double>(key,value);
			}
		}).groupBy(0).reduce(new ReduceFunction<Tuple2<String,Double>>() {

			@Override
			public Tuple2<String, Double> reduce(Tuple2<String, Double> arg0, Tuple2<String, Double> arg1)
					throws Exception {
				// TODO Auto-generated method stub
					
				return new Tuple2<String, Double>(arg0.f0,(arg0.f1+arg1.f1)/2.0) ;
			}
		});
		textttt1.print();
		///
		//
		DataStream<String> texttt = env.socketTextStream(hostname, port, "\n");
		DataStream<Tuple2<String, Double>> texttt1 = texttt.map(new MapFunction<String, Tuple2<String, Double>>() {

			@Override
			public Tuple2<String, Double> map(String arg0) throws Exception {
				// TODO Auto-generated method stub
				String a[]= arg0.split("\\s");
				String key = a[0];
				Double value = Double.parseDouble(a[1]);
				
				return new Tuple2<String, Double>(key,value);
			}
		}).keyBy(0).min(1);
		texttt1.print();
		
			
		// get input data by connecting to the socket
		/*DataStream<String> text = env.socketTextStream(hostname, port, "\n");
		DataStream<WordWithCount> text1 = text.filter(new FilterFunction<String>() {
			
			@Override
			public boolean filter(String arg0) throws Exception {
				
				if(arg0.length()>3)
					return true;
				else
					return false;
			}
		}).map(new MapFunction<String, WordWithCount>() {

			@Override
			public WordWithCount map(String arg0) throws Exception {
				// TODO Auto-generated method stub
				return new WordWithCount(arg0.toLowerCase(),1);
			}
		}).keyBy("word").reduce(new ReduceFunction<Exercise.WordWithCount>() {

			@Override
			public WordWithCount reduce(WordWithCount arg0, WordWithCount arg1) throws Exception {
				// TODO Auto-generated method stub
				return new WordWithCount(arg0.word,arg0.count+arg1.count);
			}
		});
		
		//text1.writeAsText("test2");
		text1.print();
		*/
		// parse the data, group it, window it, and aggregate the counts
		/*DataStream<WordWithCount> windowCounts = text

				.flatMap(new FlatMapFunction<String, WordWithCount>() {
					@Override
					public void flatMap(String value, Collector<WordWithCount> out) {
						for (String word : value.split("\\s")) {
							out.collect(new WordWithCount(word, 1L));
						}
					}
				})

				.keyBy("word")
				.timeWindow(Time.seconds(10),Time.seconds(2))

				.reduce(new ReduceFunction<WordWithCount>() {
					@Override
					public WordWithCount reduce(WordWithCount a, WordWithCount b) {
						return new WordWithCount(a.word, a.count + b.count);
					}
				});

		// print the results with a single thread, rather than in parallel
		windowCounts.print().setParallelism(1);*/

		env.execute("Socket Window WordCount");
	}

	// ------------------------------------------------------------------------

	/**
	 * Data type for words with count.
	 */
	public static class WordWithCount {

		public String word;
		public long count;

		public WordWithCount() {}

		public WordWithCount(String word, long count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return word + " : " + count;
		}
	}
	/**
	 * Data type for words with count.
	 */
	public static class WordWithMap {

		public String word;
		public String word1;

		public WordWithMap() {}

		public WordWithMap(String word, String word1) {
			this.word = word;
			this.word1 = word1;
		}

		@Override
		public String toString() {
			return word + " : " + word1;
		}
	}
}