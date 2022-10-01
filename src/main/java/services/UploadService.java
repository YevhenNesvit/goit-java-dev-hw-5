package services;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadService {
    private static final String PET = "https://petstore.swagger.io/v2/pet/";
    private static final String nextLine = "\r\n";
    private static final String twoHyphens = "--";
    private static final String boundary = Long.toString(System.currentTimeMillis());

    private static HttpURLConnection createConnection(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");

        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        return httpURLConnection;
    }

    public void uploadFiles(Long id, String file) {
        File uploadFile = new File("src" + File.separator + "main" + File.separator + "resources"
                + File.separator + file);
        String url = PET + id + "/uploadImage";
        uploadFile(uploadFile, url);
    }

    private static void uploadFile(File file, String url) {
        HttpURLConnection connection = null;
        OutputStream outputStream = null;
        FileInputStream inputStream = null;
        try {
            connection = createConnection(url);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();

            outputStream = new DataOutputStream(connection.getOutputStream());

            String header = twoHyphens + boundary + nextLine;

            header += "Content-Disposition: form-data;name=\"file\";" + "filename=\"" + file.getName() + "\"" + nextLine + nextLine;

            outputStream.write(header.getBytes());

            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
            }

            outputStream.write(nextLine.getBytes());

            String footer = nextLine + twoHyphens + boundary + twoHyphens + nextLine;
            outputStream.write(footer.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(connection.getInputStream()));
                StringBuffer response = new StringBuffer();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response);
            } else {
                System.err.println("Загрузка не удалась");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
