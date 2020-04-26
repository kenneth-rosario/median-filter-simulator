package Algorithms

import java.awt.Color
import java.awt.image.BufferedImage
import java.util

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object  MedianFilters {

  private def time[R](block: => R): (R, Double) = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    val diff = t1-t0
    (result, diff * 1.0 / 1E9)
  }

  // i <- 1 until img.getWidth/2 ; j <- 1 until img.getHeight - 1

  private def filterRange(img:BufferedImage, starti:Int, endi:Int, startj:Int, endj:Int) = {
    val pixelWindow1 = new Array[Color](9)
    val redWindow1 = new Array[Int](9)
    val blueWindow1 = new Array[Int](9)
    val greenWindow1 = new Array[Int](9)

    for (i <- starti until endi; j <- startj until endj) {

      pixelWindow1(0) = new Color(img.getRGB(i - 1, j - 1))
      pixelWindow1(1) = new Color(img.getRGB(i - 1, j))
      pixelWindow1(2) = new Color(img.getRGB(i - 1, j + 1))
      pixelWindow1(3) = new Color(img.getRGB(i, j - 1))
      pixelWindow1(4) = new Color(img.getRGB(i, j))
      pixelWindow1(5) = new Color(img.getRGB(i, j + 1))
      pixelWindow1(6) = new Color(img.getRGB(i + 1, j - 1))
      pixelWindow1(7) = new Color(img.getRGB(i + 1, j))
      pixelWindow1(8) = new Color(img.getRGB(i + 1, j + 1))


      for (k <- 0 until pixelWindow1.length) {
        redWindow1(k) = pixelWindow1(k).getRed
        blueWindow1(k) = pixelWindow1(k).getBlue
        greenWindow1(k) = pixelWindow1(k).getGreen
      }

      util.Arrays.sort(redWindow1)
      util.Arrays.sort(blueWindow1)
      util.Arrays.sort(greenWindow1)

      img.setRGB(i, j, new Color(redWindow1(4), greenWindow1(4), blueWindow1(4)).getRGB)
    }

  }


  def medianFilterParallel(img:BufferedImage): Future[(BufferedImage, Double)] = Future {
    time{

      val part1 = Future {
        filterRange(img, 1, img.getWidth/2, 1, img.getHeight/2)
      }
      val part2 = Future {
        filterRange(img, img.getWidth/2, img.getWidth - 1, 1, img.getHeight/2)
      }
      val part3 = Future {
        filterRange(img, 1, img.getWidth/2, img.getHeight/2, img.getHeight - 1)
      }
      val part4 = Future {
        filterRange(img, img.getWidth/2, img.getWidth - 1, img.getHeight/2, img.getHeight - 1)
      }

      Await.ready(part4, Duration.Inf)

      img
    }
  }

  def medianFilterSimple(img:BufferedImage): Future[(BufferedImage, Double)] = Future {
    time {
      filterRange(img, 1, img.getWidth - 1, 1, img.getHeight - 1)
      img
    }
  }

}
