package ar.com.smtr.monitoreo

import org.springframework.dao.DataIntegrityViolationException

class UbicacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ubicacionInstanceList: Ubicacion.list(params), ubicacionInstanceTotal: Ubicacion.count()]
    }

    def create() {
        [ubicacionInstance: new Ubicacion(params)]
    }

    def save() {
        def ubicacionInstance = new Ubicacion(params)
        if (!ubicacionInstance.save(flush: true)) {
            render(view: "create", model: [ubicacionInstance: ubicacionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), ubicacionInstance.id])
        redirect(action: "show", id: ubicacionInstance.id)
    }

    def show(Long id) {
        def ubicacionInstance = Ubicacion.get(id)
        if (!ubicacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "list")
            return
        }

        [ubicacionInstance: ubicacionInstance]
    }

    def edit(Long id) {
        def ubicacionInstance = Ubicacion.get(id)
        if (!ubicacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "list")
            return
        }

        [ubicacionInstance: ubicacionInstance]
    }

    def update(Long id, Long version) {
        def ubicacionInstance = Ubicacion.get(id)
        if (!ubicacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ubicacionInstance.version > version) {
                ubicacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'ubicacion.label', default: 'Ubicacion')] as Object[],
                          "Another user has updated this Ubicacion while you were editing")
                render(view: "edit", model: [ubicacionInstance: ubicacionInstance])
                return
            }
        }

        ubicacionInstance.properties = params

        if (!ubicacionInstance.save(flush: true)) {
            render(view: "edit", model: [ubicacionInstance: ubicacionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), ubicacionInstance.id])
        redirect(action: "show", id: ubicacionInstance.id)
    }

    def delete(Long id) {
        def ubicacionInstance = Ubicacion.get(id)
        if (!ubicacionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "list")
            return
        }

        try {
            ubicacionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ubicacion.label', default: 'Ubicacion'), id])
            redirect(action: "show", id: id)
        }
    }
}
