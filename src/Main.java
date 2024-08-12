import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args) {
        String filePath = "C:\\Users\\usuario\\Desktop\\Nueva carpeta";

        List<Double> profits = new ArrayList<>();
        List<Double> rdSpend = new ArrayList<>();
        List<Double> marketingSpend = new ArrayList<>();

        // Leer el archivo y almacenar los datos
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Omitir la primera línea (encabezados)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                profits.add(Double.parseDouble(values[0]));
                rdSpend.add(Double.parseDouble(values[1]));
                marketingSpend.add(Double.parseDouble(values[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Calcular la correlación
        double correlationProfitRDS = calculateCorrelation(profits, rdSpend);
        double correlationProfitMarketing = calculateCorrelation(profits, marketingSpend);

        // Mostrar resultados
        System.out.println("Correlación entre Profit y R&D Spend: " + correlationProfitRDS);
        System.out.println("Correlación entre Profit y Marketing Spend: " + correlationProfitMarketing);
    }

    // Método para calcular la correlación de Pearson
    public static double calculateCorrelation(List<Double> x, List<Double> y) {
        if (x.size() != y.size()) {
            throw new IllegalArgumentException("Las listas deben tener el mismo tamaño.");
        }

        int n = x.size();
        double sumX = 0.0;
        double sumY = 0.0;
        double sumXY = 0.0;
        double sumXSquare = 0.0;
        double sumYSquare = 0.0;

        for (int i = 0; i < n; i++) {
            double xi = x.get(i);
            double yi = y.get(i);

            sumX += xi;
            sumY += yi;
            sumXY += xi * yi;
            sumXSquare += xi * xi;
            sumYSquare += yi * yi;
        }

        double numerator = n * sumXY - sumX * sumY;
        double denominator = Math.sqrt((n * sumXSquare - sumX * sumX) * (n * sumYSquare - sumY * sumY));

        return denominator != 0 ? numerator / denominator : 0;
    }
}
