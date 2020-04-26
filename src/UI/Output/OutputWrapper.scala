package UI.Output

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{EventQueue, GridBagConstraints, GridBagLayout}

import UI.Output.Parallel.ParallelFrame
import UI.Output.Simple.SimpleFrame
import javax.swing.{JButton, JLabel, JPanel}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class OutputWrapper extends JPanel with ActionListener {

  private var currentPath:Option[String] = None
  private val label = new JLabel()
  private val btn = new JButton("Compute")
  private val gbc = new  GridBagConstraints()

  setLayout(new GridBagLayout())

  label.setText("Chosen Image:")
  btn.addActionListener(this)

  gbc.fill = GridBagConstraints.HORIZONTAL
  gbc.gridx = 1
  gbc.gridy=0
  add(label, gbc)

  gbc.gridx = 1
  gbc.gridy = 1
  add(btn, gbc)
  // Border
  setBounds(20, 20, 20, 20)

  def changePath(path:Option[String]):Unit = {
    if (path.isDefined){
      currentPath = path
      label.setText(path.get)
    }
  }

  override def actionPerformed(actionEvent: ActionEvent): Unit = {
    if (currentPath.isDefined){
      EventQueue.invokeLater(() => {
        Future { new ParallelFrame(currentPath) }
        Future { new SimpleFrame(currentPath) }
      })
    }
  }

}
