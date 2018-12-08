package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BarChart extends JPanel {
    private Map<String, Double> values = new HashMap<>();

    public BarChart(Map<String, Double> data) {
        values = data;
    }
    
    public BarChart() {      
    }

    public void paintComponent(Graphics g) {
        String title = "Диаграмма растрат по месяцам";
        
        super.paintComponent(g);
        
        if (values == null || values.size() == 0) {
            return;
        }
        
        double minValue = 0;
        double maxValue = 0;

        for (Map.Entry<String, Double> entry : values.entrySet()) {
            double value = entry.getValue();

            if (minValue > value) {
                minValue = value;
            }

            if (maxValue < value) {
                maxValue = value;
            }
        }

        Dimension d = getSize();
        int clientWidth = d.width + 5;
        int clientHeight = d.height;
        int barWidth = clientWidth / values.size();

        Font titleFont = new Font("SansSerif", Font.BOLD, 20);
        FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
        Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
        FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

        int titleWidth = titleFontMetrics.stringWidth(title);
        int y = titleFontMetrics.getAscent();
        int x = (clientWidth - titleWidth) / 2;
        g.setFont(titleFont);
        g.drawString(title, x, y);

        int top = titleFontMetrics.getHeight();
        int bottom = labelFontMetrics.getHeight();
        
        if (maxValue == minValue) {
            return;
        }
        
        double scale = (clientHeight - top - bottom) / (maxValue - minValue);
        y = clientHeight - labelFontMetrics.getDescent();
        g.setFont(labelFont);

        int i = 0;
        for (Map.Entry<String, Double> entry : values.entrySet()) {
            double value = entry.getValue();
            String key = entry.getKey();

            int valueX = i * barWidth + 1;
            int valueY = top * 3;
            int height = (int) (value * scale);

            if (value >= 0) {
                valueY += (int) ((maxValue - value) * scale);
            } else {
                valueY += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(Color.orange);
            g.fillRect(valueX, valueY, barWidth, height);
            g.setColor(Color.black);
            g.drawRect(valueX, valueY, barWidth, height);
            int labelWidth = labelFontMetrics.stringWidth(value + "");
            x = i * barWidth + (barWidth - labelWidth) / 2;
            g.setColor(Color.black);
            g.drawString(value + " ", x, y);
            int titlePlace = height / 2;
            g.setColor(Color.black);
            g.drawString(key, x, titlePlace);

            i++;
        }
    }

    public static void Show(Map<String, Double> data) {
        JFrame f = new JFrame();
        f.setSize(600, 800);
        f.setLocation(600, 300);
        f.getContentPane().add(new BarChart(data));
        f.setVisible(true);
    }
}
