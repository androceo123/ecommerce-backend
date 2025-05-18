const router = require("express").Router();
const ctrl = require("../controllers/habitacionController");

router.post("/", ctrl.crear);
router.get("/", ctrl.listar);
router.get("/:id", ctrl.obtener);
router.put("/:id", ctrl.actualizar);
router.delete("/:id", ctrl.borrar);

module.exports = router;
