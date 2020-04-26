package UI.Output.Interfaces

import java.awt.image.BufferedImage
import java.awt.{Dimension, GridBagConstraints}
import java.io.File

import UI.Output.OutputPanel
import javax.imageio.ImageIO
import javax.swing.{JFrame, JLabel}

trait ImageFrame extends JFrame{

  protected var currentImage:Option[BufferedImage] = None
  protected val statusText = new JLabel("Computing ...")
  protected val pa = new OutputPanel()
  protected val gbc = new  GridBagConstraints()

  pa.setPreferredSize(new Dimension(1000, 800))

  gbc.fill = GridBagConstraints.VERTICAL
  gbc.gridx = 1
  gbc.gridy= 0
  pa.add(statusText, gbc)

  setContentPane(pa)
  setVisible(true)

  pack()

  def setImage(path: String):Unit = {
    val img:BufferedImage = ImageIO.read(new File(path))
    currentImage = Some(img)
  }

}
