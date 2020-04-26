package UI.Input

import java.awt.event.ActionListener
import java.awt.{GridBagConstraints, GridBagLayout}

import javax.swing.{JButton, JLabel, JPanel}


class InputWrapper(listener: ActionListener) extends JPanel {

    private val label = new JLabel()
    private val btn = new JButton("Choose Image")
    private val gbc = new  GridBagConstraints()

    setLayout(new GridBagLayout())

    label.setText("Median Filter Simulator")
    btn.addActionListener(listener)

    gbc.fill = GridBagConstraints.HORIZONTAL
    gbc.gridx = 1
    gbc.gridy=0
    add(label, gbc)

    gbc.gridx = 1
    gbc.gridy = 1
    add(btn, gbc)
    // Border
    setBounds(20, 20, 20, 20)


}
