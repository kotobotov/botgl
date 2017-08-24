package example
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.annotation.JSExport
import scala.util.Random

@JSExport
object BotGL {


  case class Point(x: Int, y: Int){
    def +[T](p: T) =p match {
      case p:PVector => Point(x + p.x.toInt, y + p.y.toInt)
      case p:Point => Point(x + p.x, y + p.y)
    }
    def -[T](p: T) =p match {
      case p:PVector => Point(x - p.x.toInt, y - p.y.toInt)
      case p:Point => Point(x - p.x, y - p.y)
    }
    def unary_-() = new Point(-x,-y);
    implicit def pointToVectorConverter(p1: Point, p2:Point) = PVector(p2.x - p1.x, p2.y-p1.y)
    def /(d: Int) = Point(x / d, y / d)
    def *(d: Int) = Point(x * d, y * d)
  }

  //object Point{
  //  def apply(p:Point):Point = Point(p.x, p.y)
  //}

  case class PVector(x: Double, y: Double){
    def +(p: PVector) = PVector(x + p.x, y + p.y)
    def -(p: PVector) = PVector(x - p.x, y - p.y)
    def unary_-() = PVector(-x, -y)
    def /(d: Double) = PVector(x / d, y / d)
    def *(d: Double) = PVector(x * d, y * d)
    def magnitude() = {this}
    def magnitude(scaler:Double) = {
      //normalize() * scaler
      val koeff = math.sqrt(x*x+y*y)*scaler
      PVector(x/koeff, y/koeff)
    }
    def normalize() = {
      val koeff = math.sqrt(x*x+y*y)
      PVector(x/koeff, y/koeff)
    }
    def lenght() = magnitude()
    def scale(scaler:Double) = *(scaler)
  }

  object MausePos{
    var x, y = 0
    def get = Point(x,y)
  }

  object CollisionSegments{
    var gridWorld=Seq.empty[Render]

    def resetState() ={gridWorld=Seq.empty[Render]}
    //todo Create Collision Solver
    def apply(inputObject:Render) = gridWorld
  }

  trait Render{
    var location = PVector(0, 0)
    var velocity = PVector(0, 0)
    var accseleration = PVector(0, 0)
    var size = PVector(10, 10)
    def applayForce(incomingForce:PVector) = {
      accseleration += incomingForce
      this
    }
    def +(input:PVector) = {
      velocity + input
    }
    def -(input:PVector) = {
      velocity - input
    }

    def size(p:PVector):Render = {
      size = p
      this
    }
    def size( width:Int, height:Int):Render = {
      size = PVector(width,height)
      this
    }
    def location(p:PVector):Render = {
      location = p
      this
    }
    def location( x:Int, y:Int):Render = {
      location = PVector(x,y)
      this
    }

    def velocity(p:PVector):Render = {
      velocity = p
      this
    }
    def velocity( x:Int, y:Int):Render = {
      velocity = PVector(x,y)
      this
    }


    def updateState() = {
      velocity += accseleration
      location += velocity
      accseleration = PVector(0, 0)
      CollisionSegments(this)
      .foreach(collisionObject => detectCollision(collisionObject))
      this
    }

    def detectCollision(objectToCheck:Render):Render={
      objectToCheck
    }

    def solveCollision()={}


    def apply(_x: Int, _y: Int) = {
      location=new PVector(_x, _y)
      this
    }
    def apply(p:PVector) = {
      velocity+=p
      this
    }

    def toRender
  }

  def init(canvas: html.Canvas) = {
    val renderer = canvas.getContext("2d")
                   .asInstanceOf[dom.CanvasRenderingContext2D]

    canvas.style.backgroundColor = "#f8f8f8"
    canvas.height = canvas.parentElement.clientHeight
    canvas.width = canvas.parentElement.clientWidth

    renderer.lineWidth = 5
    renderer.strokeStyle = "red"
    renderer.fillStyle = "cyan"
    renderer
  }


  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val render = init(canvas)



    class Kvadrat(_x:Int, _y:Int) extends Render{
      location = PVector(_x, _y)
      def toRender = {
        render.moveTo(location.x, location.y)
        //render.lineTo()
        render.fillRect(location.x, location.y, size.x, size.y )
        //render.arc(location.x, location.y,30,0,Math.PI*2,true);
        this
        }
    }

    object Kvadrat{
      def apply(_x: Int, _y: Int) = {
        new Kvadrat(_x, _y)
      }
    }

    class Krug(_x:Int, _y:Int) extends Render{
      location = PVector(_x, _y)
      def toRender = {
        render.moveTo(location.x, location.y)
        //render.lineTo()
        render.arc(location.x, location.y,10,0,Math.PI*2,true);
        this
      }
    }

    object Krug{
      def apply(_x: Int, _y: Int) = {
        new Krug(_x, _y)
      }
    }


    var dragState = 0
    var count2=0
    var count = 0
    val fps = 60
    //ctx.canvas.width  = window.innerWidth;
    //ctx.canvas.height = window.innerHeight;
    val sceneHigh = dom.window.innerWidth
    val sceneWidth = dom.window.innerHeight

    val newSquaer = (0 to 10).map(item => Krug(item*100,Random.nextInt(300)).size(PVector(10.0, 10.0)) ).toList

    var mouse = PVector(MausePos.x, MausePos.y)
    var center = PVector(sceneWidth/2,sceneHigh/2)
    var probniy = Kvadrat(0, 0)
    probniy(mouse - center)

    val graph =    (0 to 500).map(item =>new Point(item,item*2))
    var p =new Point(0, 0)

    def clear() = {
      render.fillStyle = "black"
      render.fillRect(0, 0, sceneHigh, sceneWidth)
      render.fillStyle = "white"
    }

    def run = for (i <- 0 until 10){
      if (count % 3000 == 0) clear()
      count += 1
      //p = (p + corners(Random.nextInt(3))) / 2

      val height = 512.0 / (255 + p.y)
      val r = (p.x * height).toInt
      val g = ((255-p.x) * height).toInt
      val b = p.y
      render.fillStyle = s"rgb($g, $r, $b)"
      render.fillRect(p.x, p.y, 1, 1)
    }

var gravity = PVector(0, 2.5)
var wind = PVector(-0.3, 0)
    def draw ={
      clear()
      render.fillStyle = "white"
      render.beginPath()
      mouse = new PVector(MausePos.x, MausePos.y)
      render.moveTo(center.x, center.y)
      newSquaer.foreach{obekt =>
        obekt
      .applayForce((mouse - obekt.location).magnitude(3))
      .applayForce(PVector(0.03, 0.03))
      //.applayForce(gravity)
      //.applayForce(wind)
      //.velocity(if(obekt.location.y>400 || obekt.location.y<100 ) -obekt.velocity else obekt.velocity)
      .updateState
      .toRender

      //newSquaer.applayForce(wind)
      //newSquaer.updateState
      //newSquaer.toRender
      //  render.closePath()
        render.stroke()
        CollisionSegments.resetState()
      }
        count2 +=1
      render.lineTo(mouse.x, mouse.y)

    }

    def paintObject = {
      render.fillStyle = s"rgb(30, 10, 20)"
      render.fillRect(Random.nextDouble()*100, Random.nextDouble()*200, 5, 5)
    }
    dom.window.setInterval(() => draw, 1000/fps)
    // traditional
    def rect = canvas.getBoundingClientRect()



    canvas.onmousemove ={(e: dom.MouseEvent) =>
      MausePos.x = (e.clientX - rect.left).toInt
      MausePos.y = (e.clientY - rect.top).toInt

      if (dragState == 1) {
        render.lineTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
        render.stroke()
      }
    }
    canvas.onmouseup = {(e: dom.MouseEvent) =>
      if(dragState == 1) {
        render.fill()
        dragState = 2
      }else if (dragState == 2){
        render.clearRect(0, 0, 1000, 1000)
        dragState = 0
      }
    }
    canvas.onmousedown ={(e: dom.MouseEvent) =>
      if (dragState == 0) {
        dragState = 1
        render.beginPath()
        render.moveTo(
          e.clientX - rect.left,
          e.clientY - rect.top
        )
      }
    }
  }
}

