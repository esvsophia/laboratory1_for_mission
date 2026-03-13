package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Mission;
import org.example.Sorcerer;
import org.example.Technique;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import java.io.*;
import java.util.*;

public class MissionReader {
    public Mission read(File file) throws Exception {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".json")) {
            return readJson(file);
        } else if (name.endsWith(".xml")) {
            return readXml(file);
        } else {
            return readTxt(file);
        }
    }

    private Mission readJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(file);

            Mission mission = new Mission();

            mission.setMissionId(jsonNode.get("missionId").asText());
            mission.setOutcome(jsonNode.get("outcome").asText());
            mission.setDate(jsonNode.get("date").asText());
            mission.setLocation(jsonNode.get("location").asText());
            mission.setDamageCost(jsonNode.get("damageCost").asInt());
            mission.setCurse(
                    jsonNode.get("curse").get("name").asText(),
                    jsonNode.get("curse").get("threatLevel").asText()
            );

            if (jsonNode.has("comment")) {
                mission.setComment(jsonNode.get("comment").asText());
            } else {
                mission.setComment("");
            }

            List<Sorcerer> sorcerers = new ArrayList<>();
            for (int i = 0; i < jsonNode.get("sorcerers").size(); i++) {
                JsonNode s = jsonNode.get("sorcerers").get(i);
                Sorcerer sr = new Sorcerer(
                        s.get("name").asText(),
                        s.get("rank").asText()
                );
                sorcerers.add(sr);
            }
            mission.setSorcerers(sorcerers);

            List<Technique> techniques = new ArrayList<>();
            for (int i = 0; i < jsonNode.get("techniques").size(); i++) {
                JsonNode t = jsonNode.get("techniques").get(i);
                Technique tech = new Technique(
                        t.get("name").asText(),
                        t.get("type").asText(),
                        t.get("owner").asText(),
                        t.get("damage").asInt()
                );
                techniques.add(tech);
            }
            mission.setTechniques(techniques);

            return mission;
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла. Программа не может работать дальше");
            throw new IOException(e);
        }
    }

    private Mission readXml(File file) throws Exception {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            Mission mission = new Mission();

            mission.setMissionId(doc.getElementsByTagName("missionId").item(0).getTextContent());
            mission.setOutcome(doc.getElementsByTagName("outcome").item(0).getTextContent());
            mission.setDate(doc.getElementsByTagName("date").item(0).getTextContent());
            mission.setLocation(doc.getElementsByTagName("location").item(0).getTextContent());
            mission.setDamageCost(Integer.parseInt(doc.getElementsByTagName("damageCost").item(0).getTextContent()));

            Element curseEl = (Element) doc.getElementsByTagName("curse").item(0);
            mission.setCurse(
                    curseEl.getElementsByTagName("name").item(0).getTextContent(),
                    curseEl.getElementsByTagName("threatLevel").item(0).getTextContent()
            );

            if (doc.getElementsByTagName("comment").getLength() > 0) {
                mission.setComment(doc.getElementsByTagName("comment").item(0).getTextContent());
            } else {
                mission.setComment("");
            }

            List<Sorcerer> sorcerers = new ArrayList<>();
            NodeList sorcererNodes = doc.getElementsByTagName("sorcerer");
            for (int i = 0; i < sorcererNodes.getLength(); i++) {
                Element el = (Element) sorcererNodes.item(i);
                String name = el.getElementsByTagName("name").item(0).getTextContent();
                String rank = el.getElementsByTagName("rank").item(0).getTextContent();
                Sorcerer sr = new Sorcerer(name, rank);
                sorcerers.add(sr);
            }
            mission.setSorcerers(sorcerers);

            List<Technique> techniques = new ArrayList<>();
            NodeList techniqueNodes = doc.getElementsByTagName("technique");
            for (int i = 0; i < techniqueNodes.getLength(); i++) {
                Element el = (Element) techniqueNodes.item(i);
                Technique tech = new Technique(
                        el.getElementsByTagName("name").item(0).getTextContent(),
                        el.getElementsByTagName("type").item(0).getTextContent(),
                        el.getElementsByTagName("owner").item(0).getTextContent(),
                        Integer.parseInt(el.getElementsByTagName("damage").item(0).getTextContent())
                );
                techniques.add(tech);
            }
            mission.setTechniques(techniques);

            return mission;
        } catch (Exception e) {
            System.out.println("Программа завершена");
            throw new RuntimeException(e);
        }
    }

    private Mission readTxt(File file) throws Exception {
        Mission mission = new Mission();
        Map<String, String> flat = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                int sep = line.indexOf(':');
                if (sep == -1) continue;
                String key = line.substring(0, sep).trim();
                String value = line.substring(sep + 1).trim();
                flat.put(key, value);
            }
        }
    
        mission.setMissionId(flat.get("missionId"));
        mission.setDate(flat.get("date"));
        mission.setLocation(flat.get("location"));
        mission.setOutcome(flat.get("outcome"));
        mission.setDamageCost(Integer.parseInt(flat.get("damageCost")));
        mission.setCurse(flat.get("curse.name"), flat.get("curse.threatLevel"));
        mission.setComment(flat.getOrDefault("note", ""));
    
        List<Sorcerer> sorcerers = new ArrayList<>();
        int i = 0;
        while (flat.containsKey("sorcerer[" + i + "].name")) {
            String name = flat.get("sorcerer[" + i + "].name");
            String rank = flat.get("sorcerer[" + i + "].rank");
            sorcerers.add(new Sorcerer(name, rank));
            i++;
        }
        mission.setSorcerers(sorcerers);
    
        List<Technique> techniques = new ArrayList<>();
        int j = 0;
        while (flat.containsKey("technique[" + j + "].name")) {
            Technique tech = new Technique(
                    flat.get("technique[" + j + "].name"),
                    flat.get("technique[" + j + "].type"),
                    flat.get("technique[" + j + "].owner"),
                    Integer.parseInt(flat.get("technique[" + j + "].damage"))
            );
            techniques.add(tech);
            j++;
        }
        mission.setTechniques(techniques);
    
        return mission;
    }
}
