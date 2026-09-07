package com.example.job_portal.service;

import com.example.job_portal.dto.request.SkillRequest;
import com.example.job_portal.dto.response.SkillResponse;
import com.example.job_portal.entity.Skill;
import com.example.job_portal.exception.ResourceNotFoundException;
import com.example.job_portal.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillService
{
    private final SkillRepository skillRepository;
    public SkillService(SkillRepository skillRepository)
    {
        this.skillRepository = skillRepository;
    }

    public SkillResponse createSkill(SkillRequest request)
    {
        Skill skill = new Skill();
        skill.setName(request.getName());
        Skill savedSkill = skillRepository.save(skill);
        return mapToResponse(savedSkill);
    }

    public List<SkillResponse> getAllSkills()
    {
        return skillRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public SkillResponse getSkillById(UUID id)
    {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        return mapToResponse(skill);
    }

    public SkillResponse updateSkill(UUID id, SkillRequest request) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        skill.setName(request.getName());
        Skill updatedSkill = skillRepository.save(skill);
        return mapToResponse(updatedSkill);
    }

    public void deleteSkill(UUID id)
    {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        skillRepository.delete(skill);
    }

    private SkillResponse mapToResponse(Skill skill)
    {
        return new SkillResponse
        (
                skill.getId(),
                skill.getName()
        );
    }
}