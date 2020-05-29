const multer = require('multer')
const path = require('path')

module.exports = {
    dest: path.resolve(__dirname, '..', '..', 'uploads'),
    storage: multer.diskStorage({
        destination: (req, file, cb) => {
            cb(null, path.resolve(__dirname, '..', 'uploads'))
        },
        filename: (req, file, cb) => {

            const typeImage = file.mimetype.split('/')

            const fileName = `${req.userId}.${typeImage[1]}`
            cb(null, fileName)
        }
    }),
    limits: {
        fileSize: 7 * 1024 * 1024,
    },
    fileFilter: (req, file, cb) => {
        const allowedMimes = [
            'image/jpeg',
            'image/pjpeg',
            'image/jpg',
            'image/png',
            'image/gif',
        ]

        if (allowedMimes.includes(file.mimetype)) {
            cb(null, true)
        } else {
            cb(new Error('File invalid' + file.mimetype))
        }
    }
}