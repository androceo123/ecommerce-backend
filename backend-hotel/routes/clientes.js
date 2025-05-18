const router = require("express").Router();
const ctrl = require("../controllers/clienteController");

router.post("/", ctrl.crear);
router.get("/", ctrl.listar);

module.exports = router;