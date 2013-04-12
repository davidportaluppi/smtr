package ar.com.smtr.monitoreo

import org.springframework.dao.DataIntegrityViolationException

class DispositivoMovilController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [dispositivoMovilInstanceList: DispositivoMovil.list(params), dispositivoMovilInstanceTotal: DispositivoMovil.count()]
    }

    def create() {
        [dispositivoMovilInstance: new DispositivoMovil(params)]
    }

    def save() {
        def dispositivoMovilInstance = new DispositivoMovil(params)
        if (!dispositivoMovilInstance.save(flush: true)) {
            render(view: "create", model: [dispositivoMovilInstance: dispositivoMovilInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), dispositivoMovilInstance.id])
        redirect(action: "show", id: dispositivoMovilInstance.id)
    }

    def show(Long id) {
        def dispositivoMovilInstance = DispositivoMovil.get(id)
        if (!dispositivoMovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "list")
            return
        }

        [dispositivoMovilInstance: dispositivoMovilInstance]
    }

    def edit(Long id) {
        def dispositivoMovilInstance = DispositivoMovil.get(id)
        if (!dispositivoMovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "list")
            return
        }

        [dispositivoMovilInstance: dispositivoMovilInstance]
    }

    def update(Long id, Long version) {
        def dispositivoMovilInstance = DispositivoMovil.get(id)
        if (!dispositivoMovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dispositivoMovilInstance.version > version) {
                dispositivoMovilInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil')] as Object[],
                          "Another user has updated this DispositivoMovil while you were editing")
                render(view: "edit", model: [dispositivoMovilInstance: dispositivoMovilInstance])
                return
            }
        }

        dispositivoMovilInstance.properties = params

        if (!dispositivoMovilInstance.save(flush: true)) {
            render(view: "edit", model: [dispositivoMovilInstance: dispositivoMovilInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), dispositivoMovilInstance.id])
        redirect(action: "show", id: dispositivoMovilInstance.id)
    }

    def delete(Long id) {
        def dispositivoMovilInstance = DispositivoMovil.get(id)
        if (!dispositivoMovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "list")
            return
        }

        try {
            dispositivoMovilInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dispositivoMovil.label', default: 'DispositivoMovil'), id])
            redirect(action: "show", id: id)
        }
    }
}
