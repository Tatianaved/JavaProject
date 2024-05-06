import java.util.*;

class Ноутбук {
    private String модель;
    private int объемОЗУ;
    private int объемЖД;
    private String операционнаяСистема;
    private String цвет;

    public Ноутбук(String модель, int объемОЗУ, int объемЖД, String операционнаяСистема, String цвет) {
        this.модель = модель;
        this.объемОЗУ = объемОЗУ;
        this.объемЖД = объемЖД;
        this.операционнаяСистема = операционнаяСистема;
        this.цвет = цвет;
    }

    // Геттеры для доступа к полям класса

    public String getМодель() {
        return модель;
    }

    public int getОбъемОЗУ() {
        return объемОЗУ;
    }

    public int getОбъемЖД() {
        return объемЖД;
    }

    public String getОперационнаяСистема() {
        return операционнаяСистема;
    }

    public String getЦвет() {
        return цвет;
    }

    @Override
    public String toString() {
        return "Ноутбук{" +
                "модель='" + модель + '\'' +
                ", объемОЗУ=" + объемОЗУ +
                ", объемЖД=" + объемЖД +
                ", операционнаяСистема='" + операционнаяСистема + '\'' +
                ", цвет='" + цвет + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Ноутбук> ноутбуки = new HashSet<>();
        ноутбуки.add(new Ноутбук("Dell XPS", 16, 512, "Windows 10", "серый"));
        ноутбуки.add(new Ноутбук("MacBook Pro", 8, 256, "macOS", "серебристый"));
        ноутбуки.add(new Ноутбук("Lenovo ThinkPad", 12, 1024, "Windows 10", "черный"));

        // Фильтрация по критериям
        Map<String, Object> критерии = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите критерии фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int выбор = scanner.nextInt();
        switch (выбор) {
            case 1:
                System.out.println("Введите минимальный объем ОЗУ:");
                int минОбъемОЗУ = scanner.nextInt();
                критерии.put("объемОЗУ", минОбъемОЗУ);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int минОбъемЖД = scanner.nextInt();
                критерии.put("объемЖД", минОбъемЖД);
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                String операционнаяСистема = scanner.next();
                критерии.put("операционнаяСистема", операционнаяСистема);
                break;
            case 4:
                System.out.println("Введите цвет:");
                String цвет = scanner.next();
                критерии.put("цвет", цвет);
                break;
            default:
                System.out.println("Некорректный выбор критерия.");
                return;
        }

        // Фильтрация и вывод результатов
        Set<Ноутбук> отфильтрованные = фильтровать(ноутбуки, критерии);
        for (Ноутбук ноутбук : отфильтрованные) {
            System.out.println(ноутбук);
        }
    }

    // Метод для фильтрации ноутбуков по критериям
    public static Set<Ноутбук> фильтровать(Set<Ноутбук> ноутбуки, Map<String, Object> критерии) {
        Set<Ноутбук> результат = new HashSet<>();
        for (Ноутбук ноутбук : ноутбуки) {
            boolean подходит = true;
            for (Map.Entry<String, Object> entry : критерии.entrySet()) {
                String критерий = entry.getKey();
                Object значение = entry.getValue();
                switch (критерий) {
                    case "объемОЗУ":
                        if (ноутбук.getОбъемОЗУ() < (int) значение) {
                            подходит = false;
                        }
                        break;
                    case "объемЖД":
                        if (ноутбук.getОбъемЖД() < (int) значение) {
                            подходит = false;
                        }
                        break;
                    case "операционнаяСистема":
                        if (!ноутбук.getОперационнаяСистема().equals(значение)) {
                            подходит = false;
                        }
                        break;
                    case "цвет":
                        if (!ноутбук.getЦвет().equals(значение)) {
                            подходит = false;
                        }
                        break;
                    default:
                        System.out.println("Неизвестный критерий: " + критерий);
                        return результат;
                }
            }
            if (подходит) {
                результат.add(ноутбук);
            }
        }
        return результат;
    }
}
