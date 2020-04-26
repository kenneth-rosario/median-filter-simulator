package UI.Output.Parallel

import Algorithms.MedianFilters._
import UI.Output.Interfaces.ImageFrame
import javax.swing.{ImageIcon, JLabel}

import scala.concurrent.ExecutionContext.Implicits.global

class ParallelFrame(path:Option[String]) extends ImageFrame {

  setImage(path.get)
  setTitle("Parallel Output")
  setLocation(0, 100)

  medianFilterParallel(currentImage.get)
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
