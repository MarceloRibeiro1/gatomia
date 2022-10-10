package me.dio.gatomia.controller;

import io.swagger.annotations.Api;
import me.dio.gatomia.dto.cat.CatDto;
import me.dio.gatomia.dto.cat.CreateCatDto;
import me.dio.gatomia.dto.owner.CreateOwnerDto;
import me.dio.gatomia.dto.owner.OwnerDto;
import me.dio.gatomia.dto.treatment.CreateTreatmentDto;
import me.dio.gatomia.dto.treatment.TreatmentDto;
import me.dio.gatomia.enumeration.Solutions;
import me.dio.gatomia.handler.AppRepositoryException;
import me.dio.gatomia.model.Cats;
import me.dio.gatomia.model.Owner;
import me.dio.gatomia.model.Treatment;
import me.dio.gatomia.service.implement.CatsServiceImplements;
import me.dio.gatomia.service.implement.OwnerServiceImplements;
import me.dio.gatomia.service.implement.TreatmentServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Api(value = "/api")
public class gatomiaController {

    @Autowired
    private CatsServiceImplements catsServiceImplements;
    @Autowired
    private TreatmentServiceImplements treatmentServiceImplements;
    @Autowired
    private OwnerServiceImplements ownerServiceImplements;

    @PostMapping(value = "/create/owner")
    public ResponseEntity<OwnerDto> createOwner(@Valid @RequestBody CreateOwnerDto createOwnerDto) {
        try {
            OwnerDto createdOwner = ownerServiceImplements.createOwner(createOwnerDto);
            return ResponseEntity.ok(createdOwner);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/edit/owner/")
    public ResponseEntity<OwnerDto> editOwner(@Valid @RequestBody OwnerDto ownerDto) {
        try {
            OwnerDto editedOwner = ownerServiceImplements.editOwner(ownerDto);
            return ResponseEntity.ok(editedOwner);
        } catch (Exception e) {
            throw new AppRepositoryException("Could not edit owner  "
                    + ownerDto.getOwnerId() + " error: " + e.getMessage(), e);
        }
    }

    @GetMapping(value = "/find/owner/{ownerId}")
    public ResponseEntity<Owner> findOwner(@PathVariable("ownerId") Long ownerId) {
        try {
            Owner owner = ownerServiceImplements.getOwner(ownerId);
            return ResponseEntity.ok(owner);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete/owner/{ownerId}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("ownerId") Long ownerId) {
        try {
            ownerServiceImplements.deleteOwner(ownerId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/create/cat")
    public ResponseEntity<CatDto> createCat(@Valid @RequestBody CreateCatDto createCatDto) {
        try {
            CatDto createdCat = catsServiceImplements.createCats(createCatDto);
            return ResponseEntity.ok(createdCat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/edit/cat/")
    public ResponseEntity<CatDto> editCat(@Valid @RequestBody CatDto catDto) {
        try {
            CatDto editedCat = catsServiceImplements.editCat(catDto);
            return ResponseEntity.ok(editedCat);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/find/cat/{catId}")
    public ResponseEntity<Cats> findCat(@PathVariable("catId") Long catId) {
        try {
            Cats cats = catsServiceImplements.findCat(catId);
            return ResponseEntity.ok(cats);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete/cat/{catId}")
    public ResponseEntity<Void> deleteCat(@PathVariable("catId") Long catId) {
        try {
            catsServiceImplements.deleteCat(catId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/create/treatment")
    public ResponseEntity<TreatmentDto> createTreatment(@Valid @RequestBody CreateTreatmentDto createTreatment) {
        try {
            TreatmentDto createdTreatment = treatmentServiceImplements.createTreatment(createTreatment);
            return ResponseEntity.ok(createdTreatment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/edit/treatment")
    public ResponseEntity<TreatmentDto> editTreatment(@Valid @RequestBody TreatmentDto treatmentDto) {
        try {
            TreatmentDto editedTreatment = treatmentServiceImplements.editTreatment(treatmentDto);
            return ResponseEntity.ok(editedTreatment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping(value = "/treatment/treat")
    public ResponseEntity<Void> treat(@Valid @RequestBody TreatmentDto treatmentDto) {
        try {
            treatmentServiceImplements.treat(treatmentDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/find/treatment/{treatmentId}")
    public ResponseEntity<Treatment> findTreatment(@PathVariable("treatmentId") Long treatmentId) {
        try {
            Treatment treatment = treatmentServiceImplements.findTreatment(treatmentId);
            return ResponseEntity.ok(treatment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/find/treatment/solution/{treatment}")
    public ResponseEntity<Solutions> findSolution(@PathVariable("treatment") Long treatmentId) {
        try {
            Solutions solutions = treatmentServiceImplements.consultTreatmentSolution(treatmentId);
            return ResponseEntity.ok(solutions);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/delete/treatment/{treatment}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable("treatment") Long treatmentId) {
        try {
            treatmentServiceImplements.deleteTreatment(treatmentId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
