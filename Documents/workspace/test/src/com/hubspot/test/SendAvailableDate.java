package com.hubspot.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SendAvailableDate {
    public static class DateMap {
        public LocalDate date;
        public List<String> emails;

        public DateMap(){
            emails = new ArrayList<>();
        }

        public DateMap(LocalDate date, List<String> emails) {
            this.date = date;
            this.emails = emails;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((date == null) ? 0 : date.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            DateMap other = (DateMap) obj;
            if (date == null) {
                if (other.date != null)
                    return false;
            } else if (!date.equals(other.date))
                return false;
            return true;
        }

    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        URL url = new URL("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=a749877b6a7f12c9277a2f7eb818");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = "";
        StringBuilder content = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            content.append(line);
        }

        Map<String, Map<LocalDate, List<String>>> datemap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        JSONParser jsonParser = new JSONParser();

        JSONObject object = (JSONObject) jsonParser.parse(content.toString());
        object.values().forEach(partner ->{
            JSONArray s = (JSONArray) partner;
            s.forEach(p -> {
                JSONObject ob = (JSONObject) p;
                String country = (String) ob.get("country");
                String email = (String) ob.get("email");
                List<String> dates = (List<String>) ob.get("availableDates");
                Set<LocalDate> convertedDates = new HashSet<>();
                dates.forEach(date -> {
                    LocalDate localdate = LocalDate.parse(date, formatter);
                    convertedDates.add(localdate);
                });

                convertedDates.forEach(date -> {
                    LocalDate ld = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
                    ld = ld.plusDays(1);

                    if(convertedDates.contains(ld)) {
                        Map<LocalDate, List<String>> dm = new HashMap<>();
                        if (datemap.containsKey(country)) {

                            dm = datemap.get(country);

                            if (dm.containsKey(date)) {
                                dm.get(date).add(email);

                            } else {
                                List<String> em = new ArrayList<>();
                                em.add(email);
                                dm.put(date, em);
                            }

                        }
                        else {
                            List<String> em = new ArrayList<>();
                            em.add(email);
                            dm.put(date, em);

                        }
                        datemap.put(country, dm);

                    } else if (!datemap.containsKey(country)) {
                        datemap.put(country, new HashMap<>());

                    }
                });


            });

        });

        Map<String, DateMap> out = new HashMap<>();

        for (Map.Entry<String, Map<LocalDate, List<String>>> entry : datemap.entrySet()) {
            int size = 0;
            LocalDate date = LocalDate.MIN;
            List<String> emails = new ArrayList<>();

            for (Map.Entry<LocalDate, List<String>> en : entry.getValue().entrySet()) {
                if (en.getValue().size() > size) {
                    size = en.getValue().size();
                    date = en.getKey();
                    emails = new ArrayList<>(en.getValue());
                } else if (en.getValue().size() == size && en.getKey().isBefore(date)) {
                    date = en.getKey();
                    emails = new ArrayList<>(en.getValue());
                }
            }
            out.put(entry.getKey(), new DateMap(date, emails));
        }

        out.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.date);
            System.out.println(v.emails);

        });
    }

}
