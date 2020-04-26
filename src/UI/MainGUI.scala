package UI

import javax.swing._


class MainGUI extends JFrame {

  // Definitions
  private val mpanel = new MainPanel()

  setContentPane(mpanel)
  setLocation(600, 400)
  pack()
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setTitle("Median Filter Simulator")
  setVisible(true)

}
