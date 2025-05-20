const router = require("express").Router();
const ctrl = require("../controllers/reservasController");

router.post("/", ctrl.crearReserva);
router.get("/", ctrl.listarReservas);

module.exports = router;