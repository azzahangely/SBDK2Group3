import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SimpleWordCount {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\alcott-little-261_1MB.txt\"; // Ganti dengan path file Anda

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line;

            // Membaca teks dari file
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String text = sb.toString();

            // Menghapus karakter non-alfanumerik dan mengubah teks menjadi huruf kecil
            String cleanedText = text.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();

            // Memisahkan kata-kata menjadi array
            String[] words = cleanedText.split(" ");

            // Membuat HashMap untuk menghitung frekuensi kata
            Map<String, Integer> wordCountMap = new HashMap<>();

            // Menghitung frekuensi kata
            for (String word : words) {
                if (wordCountMap.containsKey(word)) {
                    // Jika kata sudah ada dalam map, tambahkan 1 ke frekuensi
                    wordCountMap.put(word, wordCountMap.get(word) + 1);
                } else {
                    // Jika kata belum ada dalam map, tambahkan kata dengan frekuensi 1
                    wordCountMap.put(word, 1);
                }
            }

            // Menampilkan hasil perhitungan frekuensi kata
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();
                System.out.println(word + ": " + count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
