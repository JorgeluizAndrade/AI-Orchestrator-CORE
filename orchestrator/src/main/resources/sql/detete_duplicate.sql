-- Delete rows from the 'customers' table
DELETE FROM text_analysis
WHERE ID NOT IN (
    -- Subquery to find the minimum ID for each unique Name
    SELECT MIN(ID)
    FROM text_anlts
    GROUP BY normalized_text
);