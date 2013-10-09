package org.crockeo.pong

import org.lwjgl.input.Keyboard.isKeyDown

class InputState(val up: Boolean, val down: Boolean)
class InputConfig(val up: Int, val down: Int) {
  def getInputState: InputState =
    new InputState(isKeyDown(up), isKeyDown(down))
}