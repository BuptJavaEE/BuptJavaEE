/**
 * 预览函数
 *
 * @param {*} dataUrl base64字符串
 * @param {*} cb 回调函数
 */
function toPreviewer (dataUrl, cb) {
  cb && cb(dataUrl)
}

/**
 * 图片压缩函数
 *
 * @param {*} img 图片对象
 * @param {*} fileType  图片类型
 * @param {*} maxWidth 图片最大宽度
 * @returns base64字符串
 */
function compress (img, fileType, maxWidth) {
  let canvas = document.createElement('canvas')
  let ctx = canvas.getContext('2d')

  const proportion = img.width / img.height
  const width = maxWidth
  const height = maxWidth / proportion

  canvas.width = width
  canvas.height = height

  ctx.fillStyle = '#fff'
  ctx.fillRect(0, 0, canvas.width, canvas.height)
  ctx.drawImage(img, 0, 0, width, height)

  const base64data = canvas.toDataURL(fileType, 0.75)
  canvas = ctx = null

  return base64data
}

/**
 * 选择图片函数
 *
 * @param {*} e input.onchange事件对象
 * @param {*} cb 回调函数
 * @param {number} [maxsize=200 * 1024] 图片最大体积
 */
function chooseImg (e, cb, maxsize = 200 * 1024) {
  const file = e.target.files[0]

  if (!file || !/\/(?:jpeg|jpg|png)/i.test(file.type)) {
    return
  }

  const reader = new FileReader()
  reader.onload = function () {
    const result = this.result
    let img = new Image()

    if (result.length <= maxsize) {
      toPreviewer(result, cb)
      return
    }

    img.onload = function () {
      const compressedDataUrl = compress(img, file.type, maxsize / 1024)
      toPreviewer(compressedDataUrl, cb)
      img = null
    }

    img.src = result
  }

  reader.readAsDataURL(file)
}

const onPaste = (e) => {
  if (!(e.clipboardData && e.clipboardData.items)) {
    return
  }
  return new Promise((resolve, reject) => {
    for (let i = 0, len = e.clipboardData.items.length; i < len; i++) {
      const item = e.clipboardData.items[i]
      if (item.kind === 'string') {
        item.getAsString((str) => {
          resolve(str)
        })
      } else if (item.kind === 'file') {
        const pasteFile = item.getAsFile()
        const imgEvent = {
          target: {
            files: [pasteFile]
          }
        }
        chooseImg(imgEvent, (url) => {
          resolve(url)
        })
      } else {
        reject(new Error('Not allow to paste this type!'))
      }
    }
  })
}


/**
 * 获取光标位置
 * @param {DOMElement} element 输入框的dom节点
 * @return {Number} 光标位置
 */
const getCursorPosition = (element) => {
  let caretOffset = 0
  const doc = element.ownerDocument || element.document
  const win = doc.defaultView || doc.parentWindow
  const sel = win.getSelection()
  if (sel.rangeCount > 0) {
    const range = win.getSelection().getRangeAt(0)
    const preCaretRange = range.cloneRange()
    preCaretRange.selectNodeContents(element)
    preCaretRange.setEnd(range.endContainer, range.endOffset)
    caretOffset = preCaretRange.toString().length
  }
  return caretOffset
}

/**
 * 设置光标位置
 * @param {DOMElement} element 输入框的dom节点
 * @param {Number} cursorPosition 光标位置的值
 */
const setCursorPosition = (element, cursorPosition) => {
  const range = document.createRange()
  range.setStart(element.firstChild, cursorPosition)
  range.setEnd(element.firstChild, cursorPosition)
  const sel = window.getSelection()
  sel.removeAllRanges()
  sel.addRange(range)
}

const emoji = {
  smiles: '😀 😁 😂 🤣 😃 😄 😅 😆 😉 😊 😋 😎 😍'.split(' ')
}

new Vue({
  el: '#app',
  data () {
    return {
      editor: null,
      cursorPosition: 0,
      emoji
    }
  },
  mounted () {
    this.editor = this.$refs['editor']
  },
  methods: {
    submit (e) {
      const value = e.target.innerHTML.replace(/[\n\r]$/, '')
      if (value) {
        console.info('Submit text', { value })
        e.target.innerText = ''
      }
    },
    async onPaste (e) {
      const result = await onPaste(e)
      const imgRegx = /^data:image\/png;base64,/
      if (imgRegx.test(result)) {
        // const sel = window.getSelection()
        // if (sel && sel.rangeCount === 1 && sel.isCollapsed) {
        //   const range = sel.getRangeAt(0)
        //   const img = new Image()
        //   img.src = result
        //   range.insertNode(img)
        //   range.collapse(false)
        //   sel.removeAllRanges()
        //   sel.addRange(range)
        // }

        document.execCommand('insertImage', false, result)
      } else {
        document.execCommand('insertText', false, result)
      }
    },
    getCursor () {
      this.cursorPosition = getCursorPosition(this.editor)
    },
    insertEmoji (emoji) {
      const text = this.editor.innerHTML
      this.editor.innerHTML = text.slice(0, this.cursorPosition) + emoji + text.slice(this.cursorPosition, text.length)
      setCursorPosition(this.editor, this.cursorPosition + 1)
      this.cursorPosition = getCursorPosition(this.editor) + 1 //  emoji takes 2 bytes
    }
  }
})