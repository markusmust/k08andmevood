import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

class kodutoo8 {

    public static String numbriteformat(String s) {
        String[] splits = s.split(",");

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator(' ');
        formatter.setDecimalFormatSymbols(symbols);

        return splits[0] + " - " + formatter.format(Integer.parseInt(splits[1])) + " km²";
    }

    public static Integer getSize(String s) {
        return Integer.parseInt(s.split(",")[1]);
    }

    public static void main(String[] args) throws Exception {
        //kõik üle viie miljoni ruutkm alaga suuruse järjekorras
        List<String> suurimadriigid = Files.readAllLines(Paths.get("data.txt"))
                .stream()
                .filter(s -> getSize(s) > 5000000)
                .sorted((s1, s2) -> getSize(s2) - getSize(s1))
                .map(kodutoo8::numbriteformat)
                .collect(Collectors.toList());
        Files.write(Paths.get("topsuurus.txt"), suurimadriigid);

        List<String> tahestiku = Files.readAllLines(Paths.get("data.txt"))
                .stream()
                .sorted((s1, s2) -> numbriteformat(s1).compareTo(numbriteformat(s2)))
                .map(kodutoo8::numbriteformat)
                .collect(Collectors.toList());
        Files.write(Paths.get("tahestikujärg.txt"), tahestiku);
    }
}

