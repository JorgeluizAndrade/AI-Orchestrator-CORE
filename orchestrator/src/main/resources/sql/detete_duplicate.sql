DELETE FROM text_analysis
WHERE id NOT IN (
    SELECT MIN(ID)
    FROM text_anlts
    GROUP BY normalized_text
);
