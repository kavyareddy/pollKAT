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
import java.sql.*;


@Singleton
public class ApplicationController {
    public static SQLDB  sqlDB = new SQLDB();
	public Result index() {

		return Results.html();

	}

	public Result helloWorldJson() {

		SimplePojo simplePojo = new SimplePojo();
		simplePojo.content = "Hello World! Hello Json!";

		return Results.json().render(simplePojo);

	}

	public Result accept_qsn(@PathParam("qsn") String qsn_str ) throws Exception {

		System.out.println(qsn_str);
		// Write qsn to database
		sqlDB.insertQsn(qsn_str);

		String resp = "Success!";

		return Results.text().render(resp);

	}
	public Result qsn_response(@PathParam("qsnId") String qsn_ID, @PathParam("response") String response_given) throws Exception{
		//increase response count for qsn_ID based on response in DB;
		sqlDB.updateRspns(qsn_ID,response_given);
		String resp = "Success!";
		return Results.text().render(resp);
	}

	public Result send_qsns() throws Exception {
		//String results[]  
				String qsns; //= new String("how many people are there?;how many girls are there?");//String of qsns from db
				qsns = sqlDB.getQsn();
		/*int l = results.length;
		String qsns = null;
		for(int i=0; i< l; i++){
			qsns += results[i] + "@@";
		}*/
		 
		return Results.text().render(qsns);

	}
	
	public Result send_stats() throws Exception{
		String stats =  new String("qsn1;stats1@qsn2;stats2");
		stats = sqlDB.getStats();
		return Results.text().render(stats);
	}



	public static class SimplePojo {

		public String content;

	}
}
