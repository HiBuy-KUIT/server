package hibuy.server.dto.userProduct;

public enum Day {
    월(1), 화(2), 수(3), 목(4), 금(5), 토(6), 일(7);

    private final int value;

    Day(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Day getDayByValue(int value) {
        for (Day day : Day.values()) {
            if (day.getValue() == value) {
                return day;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 요일 값입니다: " + value);
    }
}