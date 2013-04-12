package ar.com.smtr.monitoreo

import org.springframework.dao.DataIntegrityViolationException

class ChoferController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [choferInstanceList: Chofer.list(params), choferInstanceTotal: Chofer.count()]
    }

    def create() {
        [choferInstance: new Chofer(params)]
    }

    def save() {
        def choferInstance = new Chofer(params)
        if (!choferInstance.save(flush: true)) {
            render(view: "create", model: [choferInstance: choferInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'chofer.label', default: 'Chofer'), choferInstance.id])
        redirect(action: "show", id: choferInstance.id)
    }

    def show(Long id) {
        def choferInstance = Chofer.get(id)
        if (!choferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "list")
            return
        }

        [choferInstance: choferInstance]
    }

    def edit(Long id) {
        def choferInstance = Chofer.get(id)
        if (!choferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "list")
            return
        }

        [choferInstance: choferInstance]
    }

    def update(Long id, Long version) {
        def choferInstance = Chofer.get(id)
        if (!choferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (choferInstance.version > version) {
                choferInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'chofer.label', default: 'Chofer')] as Object[],
                          "Another user has updated this Chofer while you were editing")
                render(view: "edit", model: [choferInstance: choferInstance])
                return
            }
        }

        choferInstance.properties = params

        if (!choferInstance.save(flush: true)) {
            render(view: "edit", model: [choferInstance: choferInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'chofer.label', default: 'Chofer'), choferInstance.id])
        redirect(action: "show", id: choferInstance.id)
    }

    def delete(Long id) {
        def choferInstance = Chofer.get(id)
        if (!choferInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "list")
            return
        }

        try {
            choferInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'chofer.label', default: 'Chofer'), id])
            redirect(action: "show", id: id)
        }
    }
}
