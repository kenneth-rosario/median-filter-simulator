package UI.Output.Simple

import UI.Output.Interfaces.ImageFrame
import Algorithms.MedianFilters._
import javax.swing.{ImageIcon, JLabel}

import scala.concurrent.ExecutionContext.Implicits.global

class SimpleFrame(path:Option[String]) extends  ImageFrame {

  setImage(path.get)
  setTitle("Simple output")
  setLocation(1000, 60)

  medianFilterSimple(currentImage.get)
    .onComplete( r => {
      statusText.setText(s"Applied Median Filter, elapsed time: ${r.get._2.toString} s")
      val labelIcon = new JLabel(new ImageIcon(currentImage.get))

      gbc.gridx = 1
      gbc.gridy = 1
      pa.add(labelIcon, gbc)
      pa.revalidate()
      pa.repaint()
    })


}
