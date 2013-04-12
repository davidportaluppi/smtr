package ar.com.smtr.monitoreo



import org.junit.*
import grails.test.mixin.*

@TestFor(DispositivoMovilController)
@Mock(DispositivoMovil)
class DispositivoMovilControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dispositivoMovil/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dispositivoMovilInstanceList.size() == 0
        assert model.dispositivoMovilInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.dispositivoMovilInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dispositivoMovilInstance != null
        assert view == '/dispositivoMovil/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dispositivoMovil/show/1'
        assert controller.flash.message != null
        assert DispositivoMovil.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dispositivoMovil/list'

        populateValidParams(params)
        def dispositivoMovil = new DispositivoMovil(params)

        assert dispositivoMovil.save() != null

        params.id = dispositivoMovil.id

        def model = controller.show()

        assert model.dispositivoMovilInstance == dispositivoMovil
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dispositivoMovil/list'

        populateValidParams(params)
        def dispositivoMovil = new DispositivoMovil(params)

        assert dispositivoMovil.save() != null

        params.id = dispositivoMovil.id

        def model = controller.edit()

        assert model.dispositivoMovilInstance == dispositivoMovil
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dispositivoMovil/list'

        response.reset()

        populateValidParams(params)
        def dispositivoMovil = new DispositivoMovil(params)

        assert dispositivoMovil.save() != null

        // test invalid parameters in update
        params.id = dispositivoMovil.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dispositivoMovil/edit"
        assert model.dispositivoMovilInstance != null

        dispositivoMovil.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dispositivoMovil/show/$dispositivoMovil.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dispositivoMovil.clearErrors()

        populateValidParams(params)
        params.id = dispositivoMovil.id
        params.version = -1
        controller.update()

        assert view == "/dispositivoMovil/edit"
        assert model.dispositivoMovilInstance != null
        assert model.dispositivoMovilInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dispositivoMovil/list'

        response.reset()

        populateValidParams(params)
        def dispositivoMovil = new DispositivoMovil(params)

        assert dispositivoMovil.save() != null
        assert DispositivoMovil.count() == 1

        params.id = dispositivoMovil.id

        controller.delete()

        assert DispositivoMovil.count() == 0
        assert DispositivoMovil.get(dispositivoMovil.id) == null
        assert response.redirectedUrl == '/dispositivoMovil/list'
    }
}
