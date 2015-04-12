/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;

import com.google.inject.Singleton;


@Singleton
public class ApplicationController {

	public Result index() {

		return Results.html();

	}

	public Result helloWorldJson() {

		SimplePojo simplePojo = new SimplePojo();
		simplePojo.content = "Hello World! Hello Json!";

		return Results.json().render(simplePojo);

	}

	public Result accept_qsn(@PathParam("qsn") String qsn_str ) {

		System.out.println(qsn_str);
		// Write qsn to database

		String resp = "Success!";

		return Results.text().render(resp);

	}
	public Result qns_response(@PathParam("qsnId") String qsn_ID, @PathParam("response") String response_given){
		//increase response count for qsn_ID based on response in DB;
		String resp = "Success!";
		return Results.text().render(resp);
	}

	public Result send_qsns() {
		String qsns = new String("how many people are there?;how many girls are there?");//String of qsns from db
		return Results.text().render(qsns);

	}
	
	public Result send_stats(){
		String stats =  new String("qsn1;stats1@qsn2;stats2");
		return Results.text().render(stats);
	}



	public static class SimplePojo {

		public String content;

	}
}
