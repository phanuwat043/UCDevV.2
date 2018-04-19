/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

//-*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
//https://ateraimemo.com/Swing/CheckBoxesInTableCell.html
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;

public final class MainPanel extends JPanel {
    private final String[] columnNames = {"user", "rwx"};
    private final Object[][] data = {
        {"owner", 7}, {"group", 6}, {"other", 5}
    };
    private final TableModel model = new DefaultTableModel(data, columnNames) {
        @Override public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    };
    private final JTable table = new JTable(model) {
        @Override public void updateUI() {
            super.updateUI();
            getColumnModel().getColumn(1).setCellRenderer(new CheckBoxesRenderer());
            getColumnModel().getColumn(1).setCellEditor(new CheckBoxesEditor());
        }
    };

    public MainPanel() {
        super(new BorderLayout());
        table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        add(new JScrollPane(table));
        setPreferredSize(new Dimension(320, 240));
    }
    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override public void run() {
                createAndShowGUI();
            }
        });
    }
    public static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        JFrame frame = new JFrame("CheckBoxesInTableCell");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class CheckBoxesPanel extends JPanel {
    protected static final String[] TITLES = {"r", "w", "x", "test"};
    public JCheckBox[] buttons;
    @Override public void updateUI() {
        super.updateUI();
        setOpaque(false);
        setBackground(new Color(0x0, true));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        initButtons();
    }
    private void initButtons() {
        Color bgc = new Color(0x0, true);
        buttons = new JCheckBox[TITLES.length];
        for (int i = 0; i < buttons.length; i++) {
            JCheckBox b = new JCheckBox(TITLES[i]);
            b.setOpaque(false);
            b.setFocusable(false);
            b.setRolloverEnabled(false);
            b.setBackground(bgc);
            buttons[i] = b;
            add(b);
            add(Box.createHorizontalStrut(5));
        }
    }
    protected void updateButtons(Object v) {
        removeAll();
        initButtons();
        Integer i = v instanceof Integer ? (Integer) v : 0;
        buttons[0].setSelected((i & (1 << 2)) != 0);
        buttons[1].setSelected((i & (1 << 1)) != 0);
        buttons[2].setSelected((i & (1 << 0)) != 0);
    }
}

class CheckBoxesRenderer extends CheckBoxesPanel implements TableCellRenderer {
    @Override public void updateUI() {
        super.updateUI();
        setName("Table.cellRenderer");
    }
    @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        updateButtons(value);
        return this;
    }
    //public static class UIResource extends CheckBoxesRenderer implements javax.swing.plaf.UIResource {}
}


class CheckBoxesEditor extends AbstractCellEditor implements TableCellEditor {
    private final CheckBoxesPanel panel = new CheckBoxesPanel() {
        @Override public void updateUI() {
            super.updateUI();
            EventQueue.invokeLater(() -> {
                ActionMap am = getActionMap();
                for (int i = 0; i < buttons.length; i++) {
                    String title = TITLES[i];
                    am.put(title, new AbstractAction(title) {
                        @Override public void actionPerformed(ActionEvent e) {
                            Arrays.stream(buttons)
                                .filter(b -> b.getText().equals(title))
                                .findFirst()
                                .ifPresent(JCheckBox::doClick);
                            fireEditingStopped();
                        }
                    });
                }
                InputMap im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                im.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), TITLES[0]);
                im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), TITLES[1]);
                im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), TITLES[2]);
            });
        }
    };
    @Override public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        panel.updateButtons(value);
        return panel;
    }
    @Override public Object getCellEditorValue() {
        int i = 0;
        i = panel.buttons[0].isSelected() ? 1 << 2 | i : i;
        i = panel.buttons[1].isSelected() ? 1 << 1 | i : i;
        i = panel.buttons[2].isSelected() ? 1 << 0 | i : i;
        return i;
    }
}
