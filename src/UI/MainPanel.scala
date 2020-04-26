package UI

import java.awt.{Dimension, GridLayout}
import java.awt.event.{ActionEvent, ActionListener}

import UI.Input.InputWrapper
import UI.Output.OutputWrapper
import javax.swing.filechooser.FileNameExtensionFilter
import javax.swing.{JFileChooser, JPanel}

class MainPanel extends JPanel with ActionListener{

  private val input = new InputWrapper( listener=this )
  private val output = new OutputWrapper()

  setPreferredSize(new Dimension(800, 300))
  setLayout(new GridLayout(2, 1))

  add(input)
  add(output)

  override def actionPerformed(actionEvent: ActionEvent): Unit = {
    val fc = new JFileChooser()
    val fileFilter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "PNG")
    fc.setFileFilter(fileFilter)
    val response = fc.showOpenDialog(this)
    if ( response == JFileChooser.APPROVE_OPTION ) {
      output.changePath(Some(fc.getSelectedFile.toString))
    } else {
      output.changePath(None)
    }
  }

}
