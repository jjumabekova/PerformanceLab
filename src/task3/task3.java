package task3;

import com.google.gson.*;
import org.json.simple.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Неверное количество аргументов. Укажите пути к файлам values.json, tests.json и report.json.");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            // Чтение данных из файла values.json
            JsonObject valuesObject = JsonParser.parseReader(new FileReader(valuesPath)).getAsJsonObject();

            // Чтение структуры тестов из файла tests.json
            JsonObject testsObject = JsonParser.parseReader(new FileReader(testsPath)).getAsJsonObject();

            // Заполнение значениями
            fillValues(testsObject, valuesObject);

            // Запись результата в файл report.json
            FileWriter fileWriter = new FileWriter(reportPath);
            Gson gson = new Gson();
            gson.toJson(testsObject, fileWriter);
            fileWriter.close();

            System.out.println("Отчет успешно сгенерирован.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillValues(JsonObject testsObject, JsonObject valuesObject) {
        JsonArray testsArray = testsObject.getAsJsonArray("tests");
        for (JsonElement testElement : testsArray) {
            JsonObject testObject = testElement.getAsJsonObject();
            String id = testObject.get("id").getAsString();
            JsonElement valueElement = findValueById(valuesObject.getAsJsonArray("values"), id);
            if (valueElement != null) {
                testObject.addProperty("value", valueElement.getAsJsonObject().get("value").getAsString());
            }

            JsonArray nestedTests = testObject.getAsJsonArray("values");
            if (nestedTests != null) {
                for (JsonElement nestedTestElement : nestedTests) {
                    fillValues(nestedTestElement.getAsJsonObject(), valuesObject);
                }
            }
        }
    }

    private static JsonElement findValueById(JsonArray valuesArray, String id) {
        for (JsonElement valueElement : valuesArray) {
            JsonObject valueObject = valueElement.getAsJsonObject();
            if (valueObject.get("id").getAsString().equals(id)) {
                return valueObject;
            }
        }
        return null;
    }
}
