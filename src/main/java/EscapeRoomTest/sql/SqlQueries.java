package EscapeRoomTest.sql;

public class SqlQueries {

    public static String allRoomsQuery(){
        return """
                SELECT DISTINCT r.room_id, r.name AS room_name, r.difficulty
                FROM room r
                JOIN room_has_element rhe ON r.room_id = rhe.room_id
                JOIN stock_manager sm ON rhe.room_has_element_id = sm.room_has_element_id
                WHERE sm.status = 'ACTIVE';
                """;
    }

    public static String allElementsQuery(){
        return """
                SELECT
                    e.element_id,
                    e.name AS element_name,
                    e.type AS element_type,
                    e.quantity AS element_quantity
                FROM
                    room_has_element rhe
                JOIN
                    element e ON rhe.element_id = e.element_id
                JOIN
                    stock_manager sm ON rhe.room_has_element_id = sm.room_has_element_id
                WHERE
                    rhe.room_id = ?
                    AND sm.status = 'ACTIVE';
                """;
    }

    public static String updateStatusQuery(){
        return """
                UPDATE stock_manager sm
                SET sm.status = 'DELETED'
                WHERE sm.room_has_element_id IN (
                    SELECT rhe.room_has_element_id
                    FROM room_has_element rhe
                    WHERE rhe.room_id = ?);
                """;
    }
}
