import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LayoutForm {
    private final JDialog dialog = new JDialog();
    private JPanel panel;
    private JTextField textFieldWidth;
    private JTextField textFieldHeight;
    private JTextField textFieldDp;
    private JButton cancelButton;
    private JButton confirmButton;
    private JButton resetButton;

    private LayoutSizeChangeListener layoutSizeChangeListener;

    public void setListener(LayoutSizeChangeListener listener) {
        layoutSizeChangeListener = listener;
    }

    public void onShowing() {
        Dimension dimension = new Dimension(555, 255);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setPreferredSize(dimension);
        dialog.setMinimumSize(dimension);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        cancelButton.addActionListener(event -> dialog.setVisible(false));
        confirmButton.addActionListener(event -> {
            onConfirm(event);
            dialog.setVisible(false);
        });
        resetButton.addActionListener(event -> {
            if (layoutSizeChangeListener != null) {
                layoutSizeChangeListener.onChanged(new LayoutSizeInfo(-1, -1, -1));
            }
            dialog.setVisible(false);
        });
    }

    private void onConfirm(ActionEvent event) {
        int width;
        int height;
        int dp;
        try {
            width = Integer.parseInt(textFieldWidth.getText());
            height = Integer.parseInt(textFieldHeight.getText());
            dp = Integer.parseInt(textFieldDp.getText());
            if (layoutSizeChangeListener != null) {
                layoutSizeChangeListener.onChanged(new LayoutSizeInfo(width, height, dp));
            }
        } catch (NumberFormatException e) {
            width = -1;
            height = -1;
            dp = -1;
        }
    }
}